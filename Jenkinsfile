
pipeline {
    agent any

    environment {
        YOCTO_HOME = "/var/lib/jenkins/workspace/harmony-pipeline"
    }

    stages {
        stage('Initialization') {
            steps {
                script {
                    // Check if repositories exist
                    def reposExist = fileExists("$YOCTO_HOME/poky") && fileExists("$YOCTO_HOME/meta-openembedded") && fileExists("$YOCTO_HOME/meta-raspberrypi") && fileExists("$YOCTO_HOME/yocto-platoon")

                    if (!reposExist) {
                        // Clone Yocto repositories if they don't exist
                        sh "git clone -b kirkstone git://git.yoctoproject.org/poky.git $YOCTO_HOME/poky"
                        sh "git clone -b kirkstone git://git.openembedded.org/meta-openembedded $YOCTO_HOME/meta-openembedded"
                        sh "git clone -b kirkstone git://git.yoctoproject.org/meta-raspberrypi $YOCTO_HOME/meta-raspberrypi"
                        sh "git clone -b dev https://github.com/MohamedSa3eed/yocto-platoon.git $YOCTO_HOME/yocto-platoon"
                        // Initialize build environment before building the image
                        sh '''#!/bin/bash
                        source $YOCTO_HOME/poky/oe-init-build-env build-platoon
                        bitbake-layers add-layer ../meta-openembedded/meta-oe
                        bitbake-layers add-layer $YOCTO_HOME/meta-raspberrypi
                        bitbake-layers add-layer $YOCTO_HOME/yocto-platoon/meta-harmony/meta-apps
                        bitbake-layers add-layer $YOCTO_HOME/yocto-platoon/meta-harmony/meta-harmonyOS
                        '''
                        // Modify local.conf
                        sh "echo 'MACHINE = \"raspberrypi3-64\"' >> $YOCTO_HOME/build-platoon/conf/local.conf"
                        sh "echo 'DISTRO = \"harmonyOS\"' >> $YOCTO_HOME/build-platoon/conf/local.conf"
                        sh "echo 'PREFERRED_PROVIDER_virtual/kernel = \"linux-harmony\"' >> $YOCTO_HOME/build-platoon/conf/local.conf"
                }
            }
        }

        stage('Build Image') {
            steps {
                script {
                    sh "cd $YOCTO_HOME"
                    // Initialize build environment before building the image
                    sh '''#!/bin/bash
                        source $YOCTO_HOME/poky/oe-init-build-env build-platoon
                        # Build the image
                        bitbake harmony-dev-image
                        '''
                }
            }
        }
    }

    post {
        success {
            echo 'Yocto Initialization Pipeline: Success!'
            // Success actions...
        }

        failure {
            echo 'Yocto Initialization Pipeline: Failed!'
            // Failure actions...
        }
    }
}
