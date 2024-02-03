require harmony-base.inc

DESCRIPTION = "Development image for Raspberry Pi 3"
SECTION = "dev"


IMAGE_FEATURES += "tools-debug tools-sdk"
IMAGE_INSTALL:append= " vim mqttclient paho-mqtt-cpp boost"

IMAGE_ROOTFS_EXTRA_SPACE = "4194304"
