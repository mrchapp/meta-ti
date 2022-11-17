SUMMARY = "TI Foundational security Low Power Management Stub"

require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

PV = "${TIFS_LPM_STUB_FW_VERSION}"
PR = "${INC_PR}.0"

CLEANBROKEN = "1"

COMPATIBLE_MACHINE = "am62xx"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"

TARGET = "ti-fs-stub-firmware-am62x-gp-signed.bin"

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware/ti-sysfw/
	install -m 0644 ${S}/ti-sysfw/${TARGET} ${D}${nonarch_base_libdir}/firmware/ti-sysfw/${TARGET}
}

FILES:${PN} = "${nonarch_base_libdir}/firmware"
