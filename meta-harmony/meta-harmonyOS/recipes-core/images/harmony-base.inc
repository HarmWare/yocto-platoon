include recipes-core/images/core-image-base.bb

DESCRIPTION = "Base image for Raspberry Pi 3 That holds the common configuration and packages"
SECTION = "base"
LICENSE = "MIT"

COMPATIBLE_MACHINE = "^rpi$"

IMAGE_FEATURES += "ssh-server-openssh debug-tweaks"
IMAGE_INSTALL:append = " packagegroup-rpi-test"

# fonts required for the QT application
IMAGE_INSTALL:append = "\
    ttf-dejavu-sans \
    ttf-dejavu-sans-mono \
    ttf-dejavu-sans-condensed \
    ttf-dejavu-serif \
    ttf-dejavu-serif-condensed \
    ttf-dejavu-common \
"

# enable auto login for the root user on the serial console and tty1
# grap the getty@.service and serial-getty@.service files from the systemd (/lib/systemd/system/)
ROOT_GETTY ?= " \
                ${IMAGE_ROOTFS}${systemd_system_unitdir}/serial-getty@.service \
                ${IMAGE_ROOTFS}${systemd_system_unitdir}/getty@.service \
"

# function to change the ExecStart line to enable auto login
root_auto_login() {
    sed -i -e 's/^\(ExecStart *=.*getty \)/\1--autologin root /' ${ROOT_GETTY}
}

# add the function to be executed after the rootfs is created
ROOTFS_POSTPROCESS_COMMAND += "root_auto_login;"
