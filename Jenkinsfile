pipeline {
  agent any
  options {
    timeout(time: 30, unit: 'MINUTES')
  }
  tools {
    jdk 'jdk_8u144'
    gradle 'gradle_4.7'
  }
  stages {
    stage('Test') {
      steps {
        sh 'gradle clean test'
      }
      post {
        always {
          junit 'build/test-results/test/TEST-*.xml'
        }
      }
    }
  }
  post {
    success {
      withCredentials([string(credentialsId: 'discord.webhook.channel', variable: 'WEBHOOK_CHANNEL'), string(credentialsId: 'discord.webhook.token', variable: 'WEBHOOK_TOKEN')]) {
        sh "curl -X POST --data '{ \"embeds\": [{\"title\":\"DiscordIntegration\",\"type\":\"link\",\"description\":\"Build $BRANCH_NAME $BUILD_DISPLAY_NAME\",\"url\":\"$BUILD_URL\",\"color\":709124,\"fields\":[{\"name\":\"Status\",\"value\":\"$currentBuild.currentResult\"}]}] }' -H \"Content-Type: application/json\"  https://discordapp.com/api/webhooks/$WEBHOOK_CHANNEL/$WEBHOOK_TOKEN"
      }
    }
    unstable {
      withCredentials([string(credentialsId: 'discord.webhook.channel', variable: 'WEBHOOK_CHANNEL'), string(credentialsId: 'discord.webhook.token', variable: 'WEBHOOK_TOKEN')]) {
        sh "curl -X POST --data '{ \"embeds\": [{\"title\":\"DiscordIntegration\",\"type\":\"link\",\"description\":\"Build $BRANCH_NAME $BUILD_DISPLAY_NAME\",\"url\":\"$BUILD_URL\",\"color\":15989262,\"fields\":[{\"name\":\"Status\",\"value\":\"$currentBuild.currentResult\"}]}] }' -H \"Content-Type: application/json\"  https://discordapp.com/api/webhooks/$WEBHOOK_CHANNEL/$WEBHOOK_TOKEN"
      }
    }
    failure {
      withCredentials([string(credentialsId: 'discord.webhook.channel', variable: 'WEBHOOK_CHANNEL'), string(credentialsId: 'discord.webhook.token', variable: 'WEBHOOK_TOKEN')]) {
        sh "curl -X POST --data '{ \"embeds\": [{\"title\":\"DiscordIntegration\",\"type\":\"link\",\"description\":\"Build $BRANCH_NAME $BUILD_DISPLAY_NAME\",\"url\":\"$BUILD_URL\",\"color\":13698309,\"fields\":[{\"name\":\"Status\",\"value\":\"$currentBuild.currentResult\"}]}] }' -H \"Content-Type: application/json\"  https://discordapp.com/api/webhooks/$WEBHOOK_CHANNEL/$WEBHOOK_TOKEN"
      }
    }
    aborted {
      withCredentials([string(credentialsId: 'discord.webhook.channel', variable: 'WEBHOOK_CHANNEL'), string(credentialsId: 'discord.webhook.token', variable: 'WEBHOOK_TOKEN')]) {
        sh "curl -X POST --data '{ \"embeds\": [{\"title\":\"DiscordIntegration\",\"type\":\"link\",\"description\":\"Build $BRANCH_NAME $BUILD_DISPLAY_NAME\",\"url\":\"$BUILD_URL\",\"fields\":[{\"name\":\"Status\",\"value\":\"$currentBuild.currentResult\"}]}] }' -H \"Content-Type: application/json\"  https://discordapp.com/api/webhooks/$WEBHOOK_CHANNEL/$WEBHOOK_TOKEN"
      }
    }
  }
}