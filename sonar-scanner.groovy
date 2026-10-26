pipeline {
    agent any

    // tools {
    //     sonarQubeScanner 'sonarqube-scanner'
    // }

    stages {
        stage('拉取代码') {
            steps {
                checkout scm
            }
        }

        stage('SonarQube 分析') {
            steps {
                script {
                    def scannerHome = tool 'sonarqube-scanner'
                    def repoName = sh(
                        returnStdout: true,
                        script: 'basename -s .git $(git config --get remote.origin.url)'
                    ).trim()
                    
                    // 判断是否 PR/MR 构建
                    def isPR = env.CHANGE_ID != null
                    
                    withSonarQubeEnv('sonarqube') {
                        if (isPR) {
                            // Pull Request / Merge Request 分析
                            sh """
                                ${scannerHome}/bin/sonar-scanner \
                                  -Dsonar.projectKey=${repoName} \
                                  -Dsonar.projectName=${repoName} \
                                  -Dsonar.projectVersion=${env.BUILD_NUMBER} \
                                  -Dsonar.pullrequest.key=${env.CHANGE_ID} \
                                  -Dsonar.pullrequest.branch=${env.CHANGE_BRANCH ?: env.BRANCH_NAME} \
                                  -Dsonar.pullrequest.base=${env.CHANGE_TARGET}
                            """
                        } else {
                            // 普通分支分析
                            sh """
                                mvn sonar:sonar \
                                  -Dsonar.projectKey=${repoName} \
                                  -Dsonar.projectName=${repoName} \
                                  -Dsonar.projectVersion=${env.BUILD_NUMBER} \
                                  -Dsonar.branch.name=${env.BRANCH_NAME}
                            """
                        }
                    }
                }
            }
        }

        stage('质量门禁') {
            steps {
                script {
                    timeout(time: 10, unit: 'MINUTES') {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "质量门禁未通过: ${qg.status}"
                        }
                    }
                }
            }
        }
    }
}