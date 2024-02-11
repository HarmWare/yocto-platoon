require harmony-base.inc

DESCRIPTION = "Release image for Raspberry Pi 3"
SECTION = "Release"

IMAGE_INSTALL:append = " mqttclient"

platoon_conf() {
   echo "$(git ls-remote https://github.com/MohamedSa3eed/yocto-platoon.git HEAD | cut -b 1-10)-release" > ${IMAGE_ROOTFS}/etc/platoon.conf
}
addtask platoon_conf 

ROOTFS_POSTINSTALL_COMMAND += "platoon_conf;"
