require ti-ndk.inc

PV = "3_75_01_01"

LIC_FILES_CHKSUM = "file://manifest_ndk_${PV}.html;md5=ef952a30d0f1640e886bb4b60618f352"

SRC_URI[ndkzip.sha256sum] = "fd2318f0490a3eb342bc16812f30394107f098a12704c11cd9a5dc3d5a0bba64"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_SYSROOT_STRIP = "1"

COMPATIBLE_HOST ?= "null"
COMPATIBLE_HOST_ti-soc = "(.*)"
