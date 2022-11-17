inherit dos2unix

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append =  " \
    file://0001-Add-PVR-Gallium-driver.patch \
    file://0002-dri-Add-some-new-DRI-formats-and-fourccs.patch \
    file://0003-GL_EXT_sparse_texture-entry-points.patch \
    file://0004-Add-support-for-various-GLES-extensions.patch \
    file://0005-Add-EGL_IMG_cl_image-extension.patch \
    file://0006-egl-optimise-eglMakeCurrent-for-the-case-where-nothi.patch \
    file://0007-GL_EXT_shader_pixel_local_storage2-entry-points.patch \
    file://0008-GL_IMG_framebuffer_downsample-entry-points.patch \
    file://0009-GL_OVR_multiview-entry-points.patch \
    file://0010-Add-OVR_multiview_multisampled_render_to_texture.patch \
    file://0011-wayland-drm-install-wayland-drm.xml-to-the-configure.patch \
    file://0012-Enable-buffer-sharing-in-the-kms_swrast-driver.patch \
    file://0013-egl-wayland-add-support-for-RGB565-back-buffers.patch \
    file://0014-egl-automatically-call-eglReleaseThread-on-thread-te.patch \
    file://0015-egl-add-Tizen-platform-support.patch \
    file://0016-egl-add-support-for-EGL_TIZEN_image_native_surface.patch \
    file://0017-egl-wayland-post-maximum-damage-when-blitting.patch \
    file://0018-egl-wayland-flush-the-drawable-before-blitting.patch \
    file://0019-egl-tizen-create-an-internal-_EGLImage-for-each-tbm-.patch \
    file://0020-dri-use-a-supported-API-in-driCreateNewContext.patch \
    file://0021-gbm-add-gbm_bo_blit.patch \
    file://0022-gbm-don-t-assert-if-DRI-context-creation-fails.patch \
    file://0023-egl-wayland-add-pbuffer-support.patch \
    file://0024-egl-tizen-support-DRI-driver-handling-of-swap-preser.patch \
    file://0025-egl-eglBindAPI-workaround-for-dEQP-bug.patch \
    file://0026-GL_EXT_multi_draw_indirect-entry-points.patch \
    file://0027-dri-add-support-for-YUV-DRI-config.patch \
    file://0028-egl-add-support-for-EXT_yuv_surface.patch \
    file://0029-dri-add-missing-__DRI_IMAGE_COMPONENTS-define-for-EG.patch \
    file://0030-egl-wayland-expose-EXT_yuv_surface-support.patch \
    file://0031-egl-tizen-expose-EXT_yuv_surface-support.patch \
    file://0032-gbm-add-some-new-GBM-formats.patch \
    file://0033-egl-add-null-platform.patch \
    file://0034-egl-add-support-for-EXT_image_gl_colorspace.patch \
    file://0035-meson-force-C-2011-for-thread_local.patch \
    file://0036-dri2-add-support-for-swap-intervals-other-than-1.patch \
    file://0037-null_platform-add-support-for-explicit-synchronisati.patch \
    file://0038-egl-null-add-support-for-DRM-image-format-modifiers.patch \
    file://0039-egl-query-the-supported-ES2-context-version.patch \
    file://0040-meson-allow-libGL-to-be-built-without-GLX.patch \
    file://0041-egl-wayland-process-non-resized-window-movement.patch \
    file://0042-Separate-EXT_framebuffer_object-from-ARB-version.patch \
    file://0043-egl-null-add-support-for-async-flip-with-front-buffe.patch \
    file://0044-gbm-add-pbuffer-support.patch \
    file://0045-egl-null-expose-EXT_yuv_surface-support.patch \
    file://0046-dri-preserve-the-original-FD-for-driver-use.patch \
    file://0047-egl-wayland-a-linear-buffer-is-not-needed-with-DRM-f.patch \
    file://0048-dri3-a-linear-buffer-is-not-needed-with-DRM-format-m.patch \
    file://0049-egl-drm-add-support-for-DRI_PRIME-GPU-selection.patch \
    file://0050-egl-null-add-support-for-DRI_PRIME-GPU-selection.patch \
    file://0051-egl-null-introduce-NULL_DRM_DISPLAY.patch \
    file://0052-vulkan-wsi-check-the-DRI3-and-Present-XCB-reply-poin.patch \
    file://0053-vulkan-wsi-Allow-host-visible-memory-to-be-requested.patch \
    file://0054-vulkan-wsi-make-the-display-FD-available.patch \
    file://0055-pvr-wsi-add-PowerVR-Vulkan-WSI-library.patch \
    file://0056-vulkan-wsi-Disable-use-of-VK_EXT_pci_bus_info.patch \
    file://0057-vulkan-wsi-default-to-force_bgra8_unorm_first-true.patch \
    file://0058-vulkan-wsi-enable-additional-formats-for-Display.patch \
    file://0059-mesa-partially-revert-pbuffer-attribute-removal.patch \
    file://0060-egl_dri2-set-pbuffer-config-attribs-to-0-for-non-pbu.patch \
    file://0061-GL_ARB_geometry_shader4-entry-points.patch \
    file://0062-egl-wayland-add-EGL_BUFFER_PRESERVED-support.patch \
    file://0063-glapi-restore-exec-dynamic.patch \
    file://0064-vulkan-wsi-avoid-double-free-after-swapchain-create-.patch \
    file://0065-Revert-meson-check-mtls-if-has_exe_wrapper.patch \
"

GALLIUMDRIVERS:append = ",pvr"

EXTRA_OEMAKE:append = " -Dgallium-pvr-alias=tidss"

DEPENDS:append = " ti-img-rogue-driver"
DEPENDS:remove = " ti-img-rogue-driver-native"
RDEPENDS:${PN}:append = " kernel-module-pvrsrvkm"
#RDEPENDS:${PN}:remove = " kernel-module-pvrsrvkm-native"

do_install:append () {
    # remove pvr custom pkgconfig
    rm -f ${D}${datadir}/pkgconfig/wayland-drm.pc
    rmdir ${D}${datadir}/pkgconfig
}
