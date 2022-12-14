DESCRIPTION = "Provides test pruss firmware for uio based tests"
LICENSE = "BSD-3-Clause"
PV = "1.0.2.0"
require recipes-ti/includes/ti-paths.inc

S = "${WORKDIR}/git"

DEPENDS = "ti-cgt-pru-native"

BRANCH = "master"
LIC_FILES_CHKSUM = "file://Makefile;startline=1;endline=31;md5=0ee4f2c42eb6b04e37859bb55a18b144"

UIO_TEST_PRUSS_GIT_URI = "git://git.ti.com/git/processor-sdk/uio-test-pruss.git"
UIO_TEST_PRUSS_GIT_PROTOCOL = "https"
SRC_URI = "${UIO_TEST_PRUSS_GIT_URI};protocol=${UIO_TEST_PRUSS_GIT_PROTOCOL};branch=${BRANCH}"

# Corresponds to version 01.00.02.00
UIO_TEST_PRUSS_SRCREV = "e52a7b099d7203ca52519650df51419e9fef7e0c"

SRCREV = "${UIO_TEST_PRUSS_SRCREV}"

PR = "r0"

COMPATIBLE_MACHINE = "omap-a15|ti33x|ti43x"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEVICE_NAME = ""
DEVICE_NAME:append:am57xx-evm = "am57xx"
DEVICE_NAME:append:ti33x = "am33xx"
DEVICE_NAME:append:ti43x = "am43xx"

EXTRA_OEMAKE = "CGT_PRU=${TI_CGT_PRU_INSTALL_DIR} DEVICE=${DEVICE_NAME}"

do_install() {
    install -d ${D}${bindir}/
    cp -r *.bin ${D}${bindir}/
}
