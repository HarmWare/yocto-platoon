# meta-harmony — Yocto layers for HarmonyOS-style Raspberry Pi images

This repository contains Yocto layers that provide a custom `harmonyOS` distribution, a `linux-harmony` kernel variant, example applications (QT-based infotainment app, MQTT client), and image recipes targeted at Raspberry Pi 3 (64-bit). The repository also includes a `Jenkinsfile` that demonstrates CI automation for cloning required layers and building the development image.

**Status:** Work-in-progress — layers are implemented under `meta-harmony/meta-harmonyOS` and `meta-harmony/meta-apps`.

**Contents**
- **Layers:** `meta-harmony/meta-harmonyOS`, `meta-harmony/meta-apps`
- **CI:** `Jenkinsfile` (automated clone + build steps)

**Quick links**
- CI pipeline: [Jenkinsfile](Jenkinsfile)
- Layer configuration: [meta-harmony/meta-harmonyOS/conf/layer.conf](meta-harmony/meta-harmonyOS/conf/layer.conf#L1-L13)
- Distro config: [meta-harmony/meta-harmonyOS/conf/distro/harmonyOS.conf](meta-harmony/meta-harmonyOS/conf/distro/harmonyOS.conf#L1-L10)
- Dev image recipe: [meta-harmony/meta-harmonyOS/recipes-core/images/harmony-dev-image.bb](meta-harmony/meta-harmonyOS/recipes-core/images/harmony-dev-image.bb#L1-L40)
- Kernel recipe: [meta-harmony/meta-harmonyOS/recipes-kernel/linux-harmony/linux-harmony_0.1.bb](meta-harmony/meta-harmonyOS/recipes-kernel/linux-harmony/linux-harmony_0.1.bb#L1-L40)
- App layer notes: [meta-harmony/meta-apps/README](meta-harmony/meta-apps/README#L1-L200)

**Table of contents**
- Project overview
- What's included (layers & recipes)
- Prerequisites (host & Yocto versions)
- How to build (detailed steps)
- CI (`Jenkinsfile`) workflow
- Recipes and images (detailed summary)
- Kernel & BSP notes
- Application recipes
- Troubleshooting
- Contributing

## Project overview

The meta-harmony collection provides a custom Yocto distro named `harmonyOS` designed to produce images for Raspberry Pi hardware (primary target: `raspberrypi3-64`). It bundles:

- A distro configuration that enables EGL/Opengl and uses `systemd`.
- A kernel recipe (`linux-harmony`) that sets RPi kernel branches and appends a `-harmony` extension identifier.
- Two image recipes: `harmony-dev-image` (developer-oriented) and `harmony-release-image` (minimal runtime image).
- Application recipes in `meta-apps`, including a `qt-app` (infotainment) and `mqttclient` example.
- UI polish: a custom `plymouth` theme and autostart service entries for the QT application.

## What's included (high-level)

- `meta-harmony/meta-harmonyOS` — distro config, kernel recipe, image recipes, BSP/boot scripts, plymouth theme, autostart service.
- `meta-harmony/meta-apps` — example application recipes and a README placeholder for layer-specific notes.
- `Jenkinsfile` — CI script that demonstrates cloning upstream Yocto layers, registering layers, and building `harmony-dev-image`.

## Prerequisites

- Supported Yocto release: Kirkstone (see `LAYERSERIES_COMPAT_meta-harmonyOS = "kirkstone"` in the layer config).
- Host tools: standard Yocto host packages (gcc, make, python, repo/git, ccache); disk space and memory appropriate for Yocto builds (20+ GB free, 8+ GB RAM recommended).
- Required layers: `poky` (Poky reference distro), `meta-openembedded`, `meta-raspberrypi`, `meta-qt5`.

Note: The `Jenkinsfile` demonstrates cloning these layers if they are not present on the CI node.

## How to build (local development)

1. Clone required layers alongside this repository (example layout):

```bash
# from a parent directory
git clone -b kirkstone git://git.yoctoproject.org/poky.git poky
git clone -b kirkstone git://git.openembedded.org/meta-openembedded meta-openembedded
git clone -b kirkstone git://git.yoctoproject.org/meta-raspberrypi meta-raspberrypi
git clone -b kirkstone https://github.com/meta-qt5/meta-qt5.git meta-qt5
git clone <this-repo-url> meta-harmony
```

2. Initialize your build directory and add layers:

```bash
source poky/oe-init-build-env build-platoon
bitbake-layers add-layer ../meta-openembedded/meta-oe
bitbake-layers add-layer ../meta-raspberrypi
bitbake-layers add-layer ../meta-harmony/meta-apps
bitbake-layers add-layer ../meta-harmony/meta-harmonyOS
bitbake-layers add-layer ../meta-qt5
```

3. Configure `conf/local.conf` (set MACHINE, DISTRO, kernel provider):

```conf
MACHINE = "raspberrypi3-64"
DISTRO = "harmonyOS"
PREFERRED_PROVIDER_virtual/kernel = "linux-harmony"
```

4. Build an image:

```bash
bitbake harmony-dev-image   # developer image
bitbake harmony-release-image  # release production image
```

5. Output images and deploy to SD card for Raspberry Pi as usual (dd or Raspberry Pi Imager workflows).

## CI workflow (`Jenkinsfile`)

High-level steps performed by the `Jenkinsfile`:

- Verify whether required repositories exist under `YOCTO_HOME`.
- If missing, clone `poky`, `meta-openembedded`, `meta-raspberrypi`, `meta-qt5`.
- Source the Poky environment (`oe-init-build-env build-platoon`).
- Use `bitbake-layers add-layer` to register the necessary layers including `meta-harmony/meta-apps` and `meta-harmony/meta-harmonyOS`.
- Append `MACHINE`, `DISTRO`, and kernel provider to `conf/local.conf`.
- Run `bitbake harmony-dev-image`.

See `Jenkinsfile` for exact shell commands and commit-status reporting integration with GitHub.

## Recipes and images (detailed)

- `meta-harmony/meta-harmonyOS/recipes-core/images/harmony-dev-image.bb`:
  - Development image that includes `vim`, `mqttclient`, `auto-start`, `python3-pip`, `qt-app`, `ffmpeg`, `plymouth`, and `u-boot`.
  - Adds a small `platoon.conf` with a git-ref to the rootfs during `ROOTFS_POSTINSTALL_COMMAND`.

- `meta-harmony/meta-harmonyOS/recipes-core/images/harmony-release-image.bb`:
  - Release image containing `mqttclient` and `qt-app` but omitting developer-only tooling.

- `meta-harmony/meta-harmonyOS/recipes-kernel/linux-harmony/linux-harmony_0.1.bb`:
  - Declares `LINUX_VERSION`, selects Raspberry Pi kernel branches (`rpi-6.1.y`), includes kernel meta cache, and sets `LINUX_VERSION_EXTENSION = "-harmony"`.

- `meta-harmony/meta-apps/recipes-apps/qtapp/qt-app_0.1.bb`:
  - Example QT application built from a Git repository; depends on a range of QT modules and installs a binary into `${bindir}`.

- `plymouth` customizations:
  - Theme tarball included and installed under `${datadir}/plymouth/themes/harmony-theme`.

## Kernel & BSP notes

- Kernel targets Raspberry Pi 6.1.x series and uses a separate kmeta (yocto kernel cache) for the kernel metadata.
- Kernel recipe references specific `SRCREV` values for machine and meta, so you can pin or bump them as needed.

## Application recipes

- `qt-app` is configured to build with `qmake` (`inherit qmake5`) and installs a pre-built binary from the workspace. The recipe uses `${AUTOREV}` for `SRCREV` — consider pinning this for reproducible builds.
- `mqttclient` recipe pulls from a GitHub repository; check `meta-apps/README` for maintainer notes and patch process.

## Troubleshooting & tips

- Out of disk space: Yocto builds require significant disk space. Clean sstate or tmp directories, or use an external large drive for build output.
- Reproducibility: Pin `SRCREV` values for recipes that currently use `AUTOREV`.
- Host packages: Ensure required native packages are installed (see Yocto documentation for your distribution).
- Build errors in kernel: Verify `LINUX_RPI_BRANCH` and `SRCREV` values; sync appropriate `meta-raspberrypi` + kernel cache branches.
- Missing layer errors: Confirm `bitbake-layers show-layers` lists `meta-harmonyOS` and `meta-apps`.

## Contributing

- Fork the repository and submit pull requests.
- Keep recipe `LICENSE` and `LIC_FILES_CHKSUM` accurate when adding new packages.
- For application-level patches, follow the placeholder guidance in `meta-apps/README` and include tests where practical.



