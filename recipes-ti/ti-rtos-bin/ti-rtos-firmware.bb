SUMMARY = "TI RTOS prebuilt binary firmware images"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${COREBASE}/../meta-ti/licenses/TI-TFL;md5=a1b59cb7ba626b9dbbcbf00f3fbc438a"

COMPATIBLE_MACHINE = "k3"

PACKAGE_ARCH = "${MACHINE_ARCH}"

INHIBIT_DEFAULT_DEPS = "1"

inherit deploy

# First, let's try including metadata.inc that could be fetched and deployed
# by ti-rtos-metadata earlier and provide new set of CORESDK_RTOS_* variables
include ${DEPLOY_DIR_IMAGE}/metadata.inc

# Set some defaults for when metadata.inc is not available
DEFAULT_RTOS_FAMILY = "jacinto"
DEFAULT_RTOS_VERSION = "07_01_00_10"
DEFAULT_RTOS_VERSION_DOT = "07.01.00.10"

DEFAULT_RTOS_SOC = "undefined"
DEFAULT_RTOS_SOC_j7 = "j721e"
DEFAULT_RTOS_SOC_j7200-evm = "j7200"
DEFAULT_RTOS_SOC_am65xx = "am65xx"

DEFAULT_RTOS_WEBLINK = "undefined"
DEFAULT_RTOS_WEBLINK_j7 = "https://software-dl.ti.com/jacinto7/esd/processor-sdk-rtos-jacinto7/firmware/${CORESDK_RTOS_VERSION}"
DEFAULT_RTOS_WEBLINK_am65xx = "https://software-dl.ti.com/processor-sdk-rtos/esd/AM65X/firmware/${CORESDK_RTOS_VERSION}"

DEFAULT_FIRMWARE_FILE = "coresdk_rtos_${CORESDK_RTOS_SOC}_${CORESDK_RTOS_VERSION}_firmware.tar.xz"

DEFAULT_FIRMWARE_URL = "file://empty"
DEFAULT_FIRMWARE_URL_k3 = "${CORESDK_RTOS_WEBLINK}/${DEFAULT_FIRMWARE_FILE}"

DEFAULT_FIRMWARE_SHA256SUM = "unknown"
DEFAULT_FIRMWARE_SHA256SUM_j7 = "ee5fdc0d7257b1e14444a8dbb846975a6f6a189bedf2ace0f03f82c5229081fc"
DEFAULT_FIRMWARE_SHA256SUM_j7200-evm = "ae30e4c271d4ba66c352e8601d6473f084a7f7c287fc02fc283bb3031ee41c63"
DEFAULT_FIRMWARE_SHA256SUM_am65xx = "c455abeb04a0a4984a33349be56d24f8f718c0dbd4bfc2341ce7e6582a3de1e2"

# Use weak assignment for CORESDK_RTOS_* variables to use defaults if not yet set
CORESDK_RTOS_FAMILY ?= "${DEFAULT_RTOS_FAMILY}"
CORESDK_RTOS_VERSION ?= "${DEFAULT_RTOS_VERSION}"
CORESDK_RTOS_VERSION_DOT ?= "${DEFAULT_RTOS_VERSION_DOT}"
CORESDK_RTOS_SOC ?= "${DEFAULT_RTOS_SOC}"
CORESDK_RTOS_WEBLINK ?= "${DEFAULT_RTOS_WEBLINK}"
CORESDK_RTOS_FIRMWARE_URL ?= "${DEFAULT_FIRMWARE_URL}"
CORESDK_RTOS_FIRMWARE_SHA256SUM ?= "${DEFAULT_FIRMWARE_SHA256SUM}"
CORESDK_RTOS_FILE_PREFIX ?= ""
CORESDK_RTOS_FILE_SUFFIX ?= ""

# Common code below
S = "${WORKDIR}/lib/"

PV = "${CORESDK_RTOS_VERSION_DOT}"

SRC_URI = "${CORESDK_RTOS_FIRMWARE_URL}${CORESDK_RTOS_FILE_SUFFIX}"
SRC_URI[sha256sum] = "${CORESDK_RTOS_FIRMWARE_SHA256SUM}"

FILES_${PN} += "${base_libdir}"

do_install() {
	CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"
	install -d ${D}${base_libdir}
	cp ${CP_ARGS} ${S} ${D}
}

FILES_${PN} = "${base_libdir}"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP_${PN} += "arch"

do_compile[noexec] = "1"
do_configure[noexec] = "1"
