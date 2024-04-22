<h1>produtosferaAPI</h1>

<h2>Descrição</h2>
<p>A <strong>produtosferaAPI</strong> é uma API desenvolvida para gerenciar produtos e categorias. A aplicação permite criar, visualizar, atualizar e excluir produtos e categorias, bem como associar categorias a produtos. Este projeto é uma oportunidade para praticar e aprimorar conhecimentos em diversas tecnologias amplamente utilizadas no desenvolvimento de REST API.</p>
<p>Além disso, utiliza o conceito de Data Transfer Object (DTO) para transferir dados entre os serviços e a camada de apresentação de forma eficiente, contribuindo para uma arquitetura mais limpa e modular.</p>

<h2>Funcionalidades Principais</h2>
    <ul>
        <li>CRUD de produtos e categorias.</li>
        <li>Associação de categorias a produtos.</li>
        <li>Documentação da API com Swagger.</li>
        <li>Migrações de banco de dados com Flyway.</li>
    </ul>

<h2>Tecnologias Utilizadas</h2>
    <ul>
        <li>Java</li>
        <li>Spring Boot</li>
        <li>PostgreSQL</li>
        <li>Flyway</li>
        <li>Swagger</li>
    </ul>

<h2>Instalação e Configuração</h2>

<h3>Pré-requisitos</h3>
    <ul>
        <li>JDK 8 ou superior</li>
        <li>PostgreSQL</li>
        <li>Maven</li>
    </ul>

<h3>Configuração do Banco de Dados</h3>
    <p>Para configurar o banco de dados, defina as seguintes variáveis de ambiente no seu sistema:</p>
    <ul>
        <li><code>DATABASE_HOST</code>: O endereço do host onde o PostgreSQL está em execução.</li>
        <li><code>DATABASE_PORT</code>: A porta na qual o PostgreSQL está ouvindo.</li>
        <li><code>DB_NAME</code>: O nome do banco de dados a ser usado pela aplicação.</li>
        <li><code>DATABASE_USERNAME</code>: O nome de usuário do PostgreSQL.</li>
        <li><code>DATABASE_PASSWORD</code>: A senha do usuário do PostgreSQL.</li>
    </ul>
<p>Certifique-se de ajustar os valores conforme a configuração do seu ambiente PostgreSQL.</p>

<h3>Execução da Aplicação</h3>
    <ol>
        <li>Clone este repositório.</li>
        <li>Navegue até o diretório do projeto.</li>
        <li>Execute o comando <code>mvn spring-boot:run</code> para iniciar a aplicação.</li>
    </ol>

<h2>Utilização</h2>
    <p>Após iniciar a aplicação, você pode acessar a documentação da API em <a href="http://localhost:8080/swagger-ui.html">http://localhost:8080/swagger-ui.html</a> para explorar e utilizar os endpoints disponíveis.</p>

<h2>Autor</h2>
    <p>David Willian</p>

<h2>Licença</h2>
    <p>Este projeto está licenciado sob a Licença MIT - veja o arquivo <a href="LICENSE">LICENSE.md</a> para mais detalhes.</p>
