SUMMARY = "QT Platoon infotainment Application Recipe"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/HaiidyHossam/QT-Modules_Test;protocol=https;branch=main"
SRCREV = "${AUTOREV}"

DEPENDS += " qtbase qtmultimedia qtconnectivity qtquickcontrols2 cinematicexperience qtdeclarative qtmqtt "
RDEPENDS_${PN} += "qtmultimedia-qmlplugins qtmultimedia-plugins qtconnectivity-qmlplugins qtquickcontrols2-plugins cinematicexperience-plugins qtimageformats-plugins qtdeclarative-plugins qtbase-plugins"


S = "${WORKDIR}/git"

do_install:append() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/build/Infotainment_Platoon ${D}${bindir}
}

inherit qmake5
