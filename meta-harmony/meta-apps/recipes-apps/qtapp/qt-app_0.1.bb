SUMMARY = "QT Platoon infotainment Application Recipe"
LICENSE = "MIT"

SRC_URI = "file://qt-app.pro \
           file://qt-app.cpp"

DEPENDS += " qtbase"

S = "${WORKDIR}"


do_install:append() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/build/qt-app ${D}${bindir}
}

inherit qmake5
