DESCRIPTION =  "Kernel drivers for the PowerVR Rogue GPU found in the TI SoCs"
HOMEPAGE = "http://git.ti.com/graphics/ti-img-rogue-driver"
LICENSE = "MIT | GPL-2.0-only"
LIC_FILES_CHKSUM = "file://README;beginline=14;endline=19;md5=0403c7dea01a2b8232261e805325fac2"

PV = "1.0+git${SRCPV}"

inherit module features_check

REQUIRED_MACHINE_FEATURES = "rogue-gpu"

MACHINE_KERNEL_PR:append = "b"
PR = "${MACHINE_KERNEL_PR}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "j721e|j721s2|j784s4|am62xx"

DEPENDS = "virtual/kernel"

PROVIDES = "virtual/gpudriver"

BRANCH = "linuxws/kirkstone/k5.10/1.18.6276027"

SRC_URI = " \
    git://git@bitbucket.itg.ti.com/~a0499604/8xe-ddk-rogue_km.git;protocol=ssh;branch=${BRANCH} \
    file://0001-build-buildvars.mk-No-error-on-enum-conversion.patch \
"

S = "${WORKDIR}/git"

SRCREV = "${AUTOREV}"

TARGET_PRODUCT:j721e = "j721e_linux"
TARGET_PRODUCT:j721s2 = "j721s2_linux"
TARGET_PRODUCT:j784s4 = "j784s4_linux"
TARGET_PRODUCT:am62xx = "am62_linux"
PVR_BUILD = "release"
PVR_WS = "wayland"

EXTRA_OEMAKE += 'KERNELDIR="${STAGING_KERNEL_DIR}" BUILD=${PVR_BUILD} PVR_BUILD_DIR=${TARGET_PRODUCT} WINDOW_SYSTEM=${PVR_WS}'

do_install() {
    make -C ${STAGING_KERNEL_DIR} M=${B}/binary_${TARGET_PRODUCT}_${PVR_WS}_${PVR_BUILD}/target_aarch64/kbuild INSTALL_MOD_PATH=${D}${root_prefix} PREFIX=${STAGING_DIR_HOST} modules_install
}
