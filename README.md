
# Anum-Preto

Anum-Preto é um projeto desenvolvido em Java com foco em exibir dados climáticos, utilizando interfaces gráficas e integração com banco de dados.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **Swing**: Para construção da interface gráfica (GUI).
- **Hibernate**: Framework para mapeamento objeto-relacional (ORM), utilizado para integração com o banco de dados.
- **Maven**: Gerenciador de dependências e automação de builds.
- **Arquitetura Modular**: Organização em pacotes como `API`, `GUI`, e `Database`.

## Estrutura do Projeto

O projeto é dividido em vários pacotes para facilitar a organização:

- `API`: Contém classes para manipular e recuperar dados climáticos.
- `Database`: Classes para modelos de dados e utilitários para operações CRUD com Hibernate.
- `GUI`: Implementa a interface gráfica do usuário.
- `Resources`: Imagens, ícones e outros recursos visuais.

## Configuração e Uso

### Requisitos

- Java 11 ou superior.
- Maven instalado.
- Banco de dados configurado com as credenciais no arquivo de configuração do Hibernate.

### Passos

1. Clone este repositório.
   ```bash
   git clone <URL-do-repositorio>
   ```
2. Navegue até o diretório do projeto.
   ```bash
   cd Anum-Preto-main
   ```
3. Compile e execute o projeto usando Maven.
   ```bash
   mvn clean install
   mvn exec:java
   ```

## Contribuição

Contribuições são bem-vindas! Por favor, envie issues e pull requests para melhorias.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
