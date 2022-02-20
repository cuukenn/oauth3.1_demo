#### 模块

|       模块名称       |                 描述                 |
| :------------------: | :----------------------------------: |
| project-build-tools  |       项目构建配置文件存放位置       |
| project-dependencies |  项目各个模块及子模块的全局版本控制  |
|     project-core     |             项目基础模块             |
|   project-starter    | 结合springboot提供的各种自动配置模块 |
|   project-gateway    |               项目网关               |
|  project-admin-ums   |             用户管理服务             |

#### 注意事项

- mvn install后checkstyle乱码:

  先在命名行执行一下命名修改命令行编码格式后即可显示正确编码格式

  `` chcp 65001
  ``

- 修改idea的默认working_directory

  ```
  %MODULE_WORKING_DIR%
  ```

- 全局禁用git快速合并

  ```
  git config --global --unset-all merge.ff
  git config --global --add merge.ff no
  ```
