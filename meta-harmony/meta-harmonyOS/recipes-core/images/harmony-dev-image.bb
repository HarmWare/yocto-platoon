require harmony-base.inc

DESCRIPTION = "Development image for Raspberry Pi 3"
SECTION = "dev"


IMAGE_FEATURES += "tools-debug tools-sdk"
IMAGE_INSTALL:append = " vim mqttclient qt-app"

# Add the platoon.conf file to the image
platoon_conf() {
   echo "$(git ls-remote https://github.com/MohamedSa3eed/yocto-platoon.git HEAD | cut -b 1-10)-dev" > ${IMAGE_ROOTFS}/${sysconfdir}/platoon.conf
   echo "export QT_QPA_PLATFORM=eglfs" >> ${IMAGE_ROOTFS}/${sysconfdir}/profile
}

# Run the platoon_conf task after the rootfs has been created
ROOTFS_POSTINSTALL_COMMAND += "platoon_conf;"
