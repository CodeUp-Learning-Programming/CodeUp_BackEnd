# CodeUp_BackEnd
Repositório BackEnd do projeto CodeUp

## Como iniciar banco Mysql com Docker Compose

### `Baixe DOCKER DESKTOP(WINDOWS)`
- [ link para instalação do Docker Desktop](https://desktop.docker.com/win/main/amd64/Docker%20Desktop%20Installer.exe?utm_source=docker&utm_medium=webreferral&utm_campaign=dd-smartbutton&utm_location=module&_gl=1*1ab9l0j*_ga*MTIzNDQ1MTU5OS4xNjgzMDcxMDMz*_ga_XJWPQMJYHQ*MTY5NjI4MjIzMi40LjEuMTY5NjI4MjI2My4yOS4wLjA.)

### `Comando no terminal (LINUX)`
- sudo apt install docker-compose
2. ### `No terminal, entrar na pasta resources do projeto:`
- #### `Criar container`   docker-compose -f "CAMINHO ABSOLUTO DESTE ARQUIVO" up -d
- #### `Matar o container` docker-compose -f "CAMINHO ABSOLUTO DESTE ARQUIVO" down
3. ### `Console para consultas `
- Com a aplicação rodando e seguindo os passos anteriores, acesse este link abaixo no navegador, que é uma interface de consulta semelhante ao do H2( O DBeaver também pode ser utilizado).

[Acesse o link](http://localhost:8000/index.php)

#### Credenciais:

> #### username: root

> #### password: code1234 