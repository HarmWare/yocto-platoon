SUMMARY = "QT Platoon infotainment Application Recipe"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://qt-app.pro \
           file://qt-app.cpp"

DEPENDS += " qtbase"

S = "${WORKDIR}"


do_install:append() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/build/qt-app ${D}${bindir}
}

inherit qmake5
