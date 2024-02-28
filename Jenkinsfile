
void setBuildStatus(String message, String state) {
  step([
      $class: "GitHubCommitStatusSetter",
      reposSource: [$class: "ManuallyEnteredRepositorySource", url: "https://github.com/MohamedSa3eed/yocto-platoon"],
      contextSource: [$class: "ManuallyEnteredCommitContextSource", context: "ci/jenkins/build-status"],
      errorHandlers: [[$class: "ChangingBuildStatusErrorHandler", result: "UNSTABLE"]],
      statusResultSource: [ $class: "ConditionalStatusResultSource", results: [[$class: "AnyBuildResult", message: message, state: state]] ]
  ]);
}




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
                    def reposExist = fileExists("$YOCTO_HOME/poky") && fileExists("$YOCTO_HOME/meta-openembedded") && fileExists("$YOCTO_HOME/meta-raspberrypi") && fileExists("$YOCTO_HOME/meta-qt5")

                    if (!reposExist) {
                        // Clone Yocto repositories if they don't exist
                        sh "git clone -b kirkstone git://git.yoctoproject.org/poky.git $YOCTO_HOME/poky"
                        sh "git clone -b kirkstone git://git.openembedded.org/meta-openembedded $YOCTO_HOME/meta-openembedded"
                        sh "git clone -b kirkstone git://git.yoctoproject.org/meta-raspberrypi $YOCTO_HOME/meta-raspberrypi"
                        sh "git clone -b kirkstone https://github.com/meta-qt5/meta-qt5.git  $YOCTO_HOME/meta-qt5"

                        // Initialize build environment before building the image
                        sh '''#!/bin/bash
                            source $YOCTO_HOME/poky/oe-init-build-env build-platoon
                            bitbake-layers add-layer $YOCTO_HOME/meta-openembedded/meta-oe
                            bitbake-layers add-layer $YOCTO_HOME/meta-raspberrypi
                            bitbake-layers add-layer $YOCTO_HOME/meta-harmony/meta-apps
                            bitbake-layers add-layer $YOCTO_HOME/meta-harmony/meta-harmonyOS
                            bitbake-layers add-layer $YOCTO_HOME/meta-qt5


                        '''

                        // Modify local.conf
                        sh "echo 'MACHINE = \"raspberrypi3-64\"' >> $YOCTO_HOME/build-platoon/conf/local.conf"
                        sh "echo 'DISTRO = \"harmonyOS\"' >> $YOCTO_HOME/build-platoon/conf/local.conf"
                        sh "echo 'PREFERRED_PROVIDER_virtual/kernel = \"linux-harmony\"' >> $YOCTO_HOME/build-platoon/conf/local.conf"
                    }

		    }

            }
        }

        stage('Build Image') {
            steps {
                script {
                    sh "cd $YOCTO_HOME"
                    sh '''#!/bin/bash
                        source $YOCTO_HOME/poky/oe-init-build-env build-platoon
                        bitbake harmony-dev-image
                    '''
                }
            }
        }
    }

    post {

        success {
            echo 'Yocto Initialization Pipeline: Success!'
	      setBuildStatus("Build succeeded", "SUCCESS");

 
        }

        failure {
            echo 'Yocto Initialization Pipeline: Fail'
	      setBuildStatus("Build succeeded", "FAILURE");

        }
    }
}
