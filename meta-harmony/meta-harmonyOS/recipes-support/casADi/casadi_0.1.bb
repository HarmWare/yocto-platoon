SUMMARY = "casADi C++ library"
DESCRIPTION = "casADi is a symbolic framework for algorithmic differentiation and optimal control. It is a C++ library with Python bindings."

# The license for the recipe
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# The URL for the recipe
SRC_URI = "git://github.com/casadi/casadi.git;protocol=https;branch=main"
SRCREV = "36d4dd629f236a78cc29b03767b317b9c9be984a"

S = "${WORKDIR}/git"

inherit cmake
