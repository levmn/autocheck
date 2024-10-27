# AutoCheck 🚗

**AutoCheck** foi desenvolvida em parceria com a **Porto Seguro** para otimizar a gestão de agendamentos e diagnósticos automotivos. A aplicação permite que usuários façam agendamentos de serviços e acompanhem diagnósticos de veículos de maneira eficiente.

## Equipe

| **Nome**                   | **RM**      |
|------------------------|---------|
| **Allan Brito Moreira**    | RM558948 |
| **Caio Liang**             | RM558868 |
| **Levi Magni**             | RM98276  |

## Estrutura do Projeto
- **.env.sample**: Arquivo de configuração na raiz do projeto que deve ser duplicado e renomeado para `.env`, onde serão inseridas as credenciais do banco de dados.
- **jdbc**: Diretório que contém o arquivo `.jar` do **ojdbc** para conexão com o banco de dados Oracle e o script SQL para criação das tabelas.
  
  ```
  src/
  ├── br/
      ├── com/
          ├── autocheck/
              ├── jdbc/
                  └── script.sql
                  └── ojdbc11.jar
  ```
## Instruções para Rodar o Projeto

1. Clone o repositório:
  ```bash
  git clone https://github.com/levmn/autocheck.git
  ```
    
  ```bash
  cd autocheck
  ```

2. Duplique o arquivo `.env.sample` para `.env` e insira as suas credenciais:
  ```
  DB_URL=jdbc:oracle:thin:@<host>:<port>:<service>
  DB_USER=<seu_usuario>
  DB_PASSWORD=<sua_senha>
  ```

3. Adicione o `ojdbc11.jar` ao classpath do projeto.




