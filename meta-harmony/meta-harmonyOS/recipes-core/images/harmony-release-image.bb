require harmony-base.inc

DESCRIPTION = "Release image for Raspberry Pi 3"
SECTION = "Release"

IMAGE_INSTALL:append = " mqttclient"

# Add the platoon.conf file to the image
platoon_conf() {
   echo "$(git ls-remote https://github.com/MohamedSa3eed/yocto-platoon.git HEAD | cut -b 1-10)-release" > ${IMAGE_ROOTFS}/etc/platoon.conf
}
# Add the platoon_conf task to the rootfs creation process
addtask platoon_conf 

# Run the platoon_conf task after the rootfs has been created
ROOTFS_POSTINSTALL_COMMAND += "platoon_conf;"
