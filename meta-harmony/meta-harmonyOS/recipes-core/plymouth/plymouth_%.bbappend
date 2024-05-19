FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = " \
    file://harmony-theme.tar.gz \
"
PACKAGECONFIG = "pango drm"

EXTRA_OECONF += "--with-udev --with-runtimedir=/run"

do_install:append() {
    install -d ${D}${datadir}/plymouth/themes/harmony-theme
    cp -r ${WORKDIR}/harmony-theme  ${D}${datadir}/plymouth/themes/
}
