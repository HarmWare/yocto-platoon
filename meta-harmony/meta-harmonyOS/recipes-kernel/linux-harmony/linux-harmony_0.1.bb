LINUX_VERSION ?= "6.1.77"
LINUX_RPI_BRANCH ?= "rpi-6.1.y"
LINUX_RPI_KMETA_BRANCH ?= "yocto-6.1"

SRCREV_machine = "77fc1fbcb5c013329af9583307dd1ff3cd4752aa"
SRCREV_meta = "43d1723dbe0ce7b341cf32feeb35ecbe6b0ce29a"

KMETA = "kernel-meta"

SRC_URI = " \
    git://github.com/raspberrypi/linux.git;name=machine;branch=${LINUX_RPI_BRANCH};protocol=https \
    git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=${LINUX_RPI_KMETA_BRANCH};destsuffix=${KMETA} \
    file://powersave.cfg \
    file://android-drivers.cfg \
    "

require recipes-kernel/linux/linux-raspberrypi.inc

KERNEL_DTC_FLAGS += "-@ -H epapr"

LINUX_VERSION_EXTENSION = "-harmony"
