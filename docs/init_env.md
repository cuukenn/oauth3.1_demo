#### 环境准备

- 基础环境要求

  - MySQL数据库
  - nacos注册中心

- 基础环境安装

  - 安装

    - 安装docker：https://www.runoob.com/docker/centos-docker-install.html
    - 安装docker-compose：https://www.runoob.com/docker/docker-compose.html
    - 安装mysql

    - nacos

      - docker运行nacos：

        - 下载nacos docker compose

          https://hub.fastgit.xyz/nacos-group/nacos-docker

        - 启动nacos服务

          ```shell
          docker-compose -f example/standalone-derby.yaml up
          ```

      - 物理机运行nacos：自行百度

  - 数据库表

    - 创建cuukenn数据库
    - 执行项目数据库sql
      - 先执行all-schemas.sql创建表结构
      - 后执行其他的sql创建初始数据