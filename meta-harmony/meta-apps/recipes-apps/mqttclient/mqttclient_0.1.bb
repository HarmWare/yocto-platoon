SUMMARY = "rpi client with paho mqtt 4 harmoney project"
DESCRIPTION =  "Simple helloworld application from github"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
DEPENDS += "paho-mqtt-cpp boost"

SRC_URI = "git://github.com/kareemAbd0/harmony-rpi-;protocol=https;branch=main"

SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit cmake
