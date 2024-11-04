# AutoCheck 🚗

**AutoCheck** foi desenvolvida em parceria com a **Porto Seguro** para otimizar a gestão de agendamentos e diagnósticos
automotivos. A aplicação permite que usuários façam agendamentos de serviços e acompanhem diagnósticos de veículos de
maneira eficiente.

## Equipe

| **Nome**                | **RM**   |
|-------------------------|----------|
| **Allan Brito Moreira** | RM558948 |
| **Caio Liang**          | RM558868 |
| **Levi Magni**          | RM98276  |

## Estrutura do Projeto

- **.env.sample**: Arquivo de configuração na raiz do projeto que deve ser duplicado e renomeado para `.env`, onde serão
  inseridas as credenciais do banco de dados.
- **jdbc**: Diretório que contém o arquivo `.jar` do **ojdbc** para conexão com o banco de dados Oracle e o script SQL
  para criação das tabelas.

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

   - **Eclipse:** No Eclipse, é necessário adicionar o arquivo `ojdbc11.jar` ao classpath do projeto manualmente. Para fazer isso:
     - Clique com o botão direito no projeto **> Properties > Java Build Path > Libraries > Add External JARs...**
     - Selecione o arquivo `ojdbc11.jar` na pasta **jdbc**.

   - **IntelliJ:** No IntelliJ, a inclusão do `ojdbc11.jar` no classpath não é necessária, pois o IntelliJ lida com as dependências automaticamente.

4. **Inicie a aplicação:** A classe Main localizada no pacote **br.com.autocheck.app** é a responsável por subir o servidor e iniciar o projeto. Para executá-la:

   - Navegue até a classe Main em `src/br/com/autocheck/app/Main.java`.
   - Execute a classe Main para iniciar o servidor com o Grizzly HTTP.

A aplicação ficará disponível em http://localhost:8080/.
