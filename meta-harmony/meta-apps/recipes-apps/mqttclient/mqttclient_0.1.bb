SUMMARY = "rpi client with paho mqtt 4 harmoney project"
DESCRIPTION =  "Simple helloworld application from github"

# The license for the recipe
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# The dependencies for the recipe
DEPENDS += "paho-mqtt-cpp boost"

# The URL for the git repository
SRC_URI = "git://github.com/kareemAbd0/harmony-rpi-;protocol=https;branch=main"

# Always use the latest commit
SRCREV = "${AUTOREV}"

# Set the workdir to the git directory
S = "${WORKDIR}/git"

inherit cmake
