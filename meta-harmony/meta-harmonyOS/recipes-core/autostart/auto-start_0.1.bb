SUMMARY = "Platoon startup systemd service"
DESCRIPTION = "This recipe installs a systemd service file for platoon startup script to run at boot time. (mqttclient and qt-app)"

# The license for the recipe
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE_${PN} = "platoon_startup.service"

SRC_URI:append = " file://platoon_startup.service "
FILES:${PN} += "${systemd_unitdir}/system/platoon_startup.service"

do_install:append() {
  install -d ${D}/${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/platoon_startup.service ${D}/${systemd_unitdir}/system
}
