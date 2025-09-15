# API ReST para Auto-Escola

## Descrição
Este projeto implementa uma API ReST desenvolvida com Spring Boot para o sistema de agendamento de instruções de uma auto-escola. A API permite o cadastro, listagem, atualização e exclusão (lógica) de instrutores e alunos, além do agendamento e cancelamento de instruções.

## Integrantes do Grupo
-Camila do Prado Padalino - RM98316
-Gabriel Teixeira Machado - RM551570
- João Pedro de Souza Vieira - RM99805


## Tecnologias Utilizadas
- Java 17
- Spring Boot 3.2.9
- Spring Data JPA
- H2 Database (em memória)
- Maven

## Funcionalidades Implementadas

### Instrutores
- **Cadastro**: Permite cadastrar novos instrutores com todas as informações obrigatórias
- **Listagem**: Lista instrutores ativos com paginação (10 registros por página) e ordenação por nome
- **Atualização**: Permite atualizar nome, telefone e endereço (e-mail, CNH e especialidade não podem ser alterados)
- **Exclusão**: Exclusão lógica que torna o instrutor inativo no sistema

### Alunos
- **Cadastro**: Permite cadastrar novos alunos com todas as informações obrigatórias
- **Listagem**: Lista alunos ativos com paginação (10 registros por página) e ordenação por nome
- **Atualização**: Permite atualizar nome, telefone e endereço (e-mail e CPF não podem ser alterados)
- **Exclusão**: Exclusão lógica que torna o aluno inativo no sistema

### Agendamentos
- **Agendamento de Instruções**: Permite agendar instruções com validações de horário de funcionamento, antecedência mínima, limite de instruções por aluno e disponibilidade de instrutor
- **Cancelamento de Instruções**: Permite cancelar instruções com antecedência mínima de 24 horas e motivo obrigatório

## Regras de Negócio Implementadas

### Horário de Funcionamento
- Segunda a sábado, das 06:00 às 21:00
- Instruções têm duração fixa de 1 hora

### Validações de Agendamento
- Antecedência mínima de 30 minutos para agendamento
- Máximo de 2 instruções por dia para o mesmo aluno
- Instrutor não pode ter conflito de horário
- Seleção automática de instrutor disponível quando não especificado

### Validações de Cancelamento
- Antecedência mínima de 24 horas
- Motivo obrigatório (Aluno desistiu, Instrutor cancelou, Outros)

## Estrutura do Projeto

```
src/main/java/com/autoescola/api/
├── entity/          # Entidades JPA
├── dto/             # Data Transfer Objects
├── vo/              # Value Objects
├── repository/      # Repositórios JPA
├── service/         # Camada de serviços
└── controller/      # Controladores REST
```

## Como Executar

1. Clone o repositório
2. Navegue até o diretório do projeto
3. Execute o comando: `mvn spring-boot:run`
4. A aplicação estará disponível em: `http://localhost:8080`

## Endpoints da API

### Instrutores
- `POST /api/instrutores` - Cadastrar instrutor
- `GET /api/instrutores` - Listar instrutores (com paginação)
- `PUT /api/instrutores/{id}` - Atualizar instrutor
- `DELETE /api/instrutores/{id}` - Excluir instrutor (lógica)

### Alunos
- `POST /api/alunos` - Cadastrar aluno
- `GET /api/alunos` - Listar alunos (com paginação)
- `PUT /api/alunos/{id}` - Atualizar aluno
- `DELETE /api/alunos/{id}` - Excluir aluno (lógica)

### Agendamentos
- `POST /api/agendamentos` - Agendar instrução
- `PUT /api/agendamentos/cancelar` - Cancelar instrução

## Console H2
Para acessar o console do banco H2 durante o desenvolvimento:
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `password`

## Observações
- O banco de dados H2 é configurado em memória para facilitar o desenvolvimento e testes
- Todas as validações de negócio foram implementadas conforme especificação
- A API utiliza exclusão lógica para manter histórico dos dados

