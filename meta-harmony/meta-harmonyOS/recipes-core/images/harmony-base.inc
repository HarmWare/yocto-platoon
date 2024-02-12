include recipes-core/images/core-image-base.bb

DESCRIPTION = "Base image for Raspberry Pi 3 That holds the common configuration and packages"
SECTION = "base"
LICENSE = "MIT"

COMPATIBLE_MACHINE = "^rpi$"

// Add packages ssh-server-openssh and debug-tweaks to the image
IMAGE_FEATURES += "ssh-server-openssh debug-tweaks"

// Add packagegroup-rpi-test to the image
IMAGE_INSTALL:append = " packagegroup-rpi-test"
