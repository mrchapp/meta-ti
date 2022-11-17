FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

python () {
    if bb.utils.contains('MACHINE_FEATURES', 'rogue-gpu', True, False, d):
        d.appendVar(
            'SRC_URI',
            """
            file://0001-libsync-add-support-for-pre-v4.7-kernels.patch
            file://0002-Add-sync_fence_info-and-sync_pt_info.patch
            file://0003-Add-sync_file_info-and-sync_get_fence_info.patch
            """
        )
}
