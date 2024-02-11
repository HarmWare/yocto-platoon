require harmony-base.inc

DESCRIPTION = "Development image for Raspberry Pi 3"
SECTION = "dev"


IMAGE_FEATURES += "tools-debug tools-sdk"
IMAGE_INSTALL:append= " vim mqttclient"

platoon_conf() {
   echo "$(git ls-remote https://github.com/MohamedSa3eed/yocto-platoon.git HEAD | cut -b 1-10)-dev" > ${IMAGE_ROOTFS}/etc/platoon.conf
}
addtask platoon_conf 

ROOTFS_POSTINSTALL_COMMAND += "platoon_conf;"
