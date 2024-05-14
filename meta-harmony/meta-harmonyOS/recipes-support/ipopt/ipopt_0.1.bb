SUMMARY = "Ipopt: Interior Point Optimizer"
DESCRIPTION = "Ipopt (Interior Point OPTimizer, pronounced I-P-Opt) \
    is a software package for large-scale nonlinear optimization."

# The license for the recipe
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# The URL for the recipe
SRC_URI = "git://github.com/coin-or/Ipopt.git;protocol=https;branch=stable/3.14"
SRCREV = "43a63412f73d9ad3ff9074d38b49362fc9d7f8f1"

S = "${WORKDIR}/git"

DEPENDS = "lapack"

inherit pkgconfig

FILES:${PN} += "${includedir}"
FILES:${PN} += "${libdir}"
FILES:${PN} += "${docdir}"

do_configure() {
        ./configure --host=${TARGET_SYS} --prefix=${prefix}
}

do_compile() {
        oe_runmake 
}

do_install() {
        oe_runmake install DESTDIR=${D}
}
