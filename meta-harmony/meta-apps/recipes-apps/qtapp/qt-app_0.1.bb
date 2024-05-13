SUMMARY = "QT Platoon infotainment Application Recipe"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://main.cpp \
           file://mainwindow.cpp \
           file://mainwindow.h \
           file://mainwindow.ui \
           file://flash.cpp \
           file://flash.h \
           file://mp3.h \
           file://mp3.cpp \
           file://mp3.ui \
           file://mp4.h \
           file://mp4.cpp \
           file://mp4.ui \
           file://Bluetooth.h \
           file://Bluetooth.cpp \
           file://Bluetooth.ui \
           file://Prayer.h \
           file://Prayer.cpp \
           file://Prayer.ui \
           file://Camera.h \
           file://Camera.cpp \
           file://Camera.ui \
           file://Weather.h \
           file://Weather.cpp \
           file://Weather.ui \
           file://popNotify.cpp \
           file://popNotify.h \
           file://popNotify.ui \
           file://Info.h \
           file://Info.cpp \
           file://Info.ui \
           file://Settings.h \
           file://Settings.cpp \
           file://Settings.ui \
           file://Infotainment_Platoon.pro \
           file://Resources.qrc \
           file://icons \
           file://sound \
           "

DEPENDS += " qtbase qtmultimedia qtconnectivity qtquickcontrols2 cinematicexperience qtimageformats qtdeclarative"
RDEPENDS_${PN} += "qtmultimedia-qmlplugins qtmultimedia-plugins qtconnectivity-qmlplugins qtquickcontrols2-plugins cinematicexperience-plugins qtimageformats-plugins qtdeclarative-plugins qtbase-plugins"


S = "${WORKDIR}"

do_install:append() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/build/Infotainment_Platoon ${D}${bindir}
}

inherit qmake5
