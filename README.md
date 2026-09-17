# Destrava PDVs

Aplicativo desktop em **Java (Swing)** usado pela equipe de TI do **Supermercado Castanha** para destravar remotamente os PDVs (caixas / pontos de venda) da loja quando o software de frente de caixa (Venditor) trava.

## Funcionamento

1. **Tela de login** (`TelaLogin`) — solicita uma senha de acesso antes de liberar a tela principal.
2. **Tela principal** (`DestravaPdvs`) — apresenta um botão para cada caixa (Caixa 01 a Caixa 20).
3. Ao selecionar um caixa, é aberta a respectiva tela (`Caixa01` … `Caixa20`), que se conecta via **SSH** (biblioteca [JSch](http://www.jcraft.com/jsch/)) ao IP daquele PDV e permite:
   - **Matar o processo do Venditor** (`killall`) quando a tela trava.
   - **Reiniciar** o PDV remotamente.
   - Outras ações de manutenção (ex.: liberar impressora de cupom, verificar disco), conforme os botões de cada tela.

## Estrutura do projeto

```
src/br/com/castanha/
  DestravaPdvs.java     # Tela principal com a lista de caixas
  TelaLogin.java        # Tela de login de acesso ao aplicativo
  Caixa01.java … Caixa20.java  # Tela de ações para cada PDV (conexão SSH individual)
  ModernUi.java          # Estilização das telas Swing
build.xml               # Build Ant (projeto NetBeans)
nbproject/               # Configuração do projeto NetBeans
```

## Tecnologias

- **Java** com **Swing** (interface gráfica) e **NetBeans** (projeto Ant/NetBeans).
- **JSch** para conexão SSH com os PDVs.

## Configuração de credenciais

A senha SSH usada para conectar aos PDVs e os PINs de acesso ao aplicativo não ficam mais no código-fonte. Eles são lidos, em ordem de prioridade, de variáveis de ambiente ou de um arquivo `config.properties` colocado ao lado do `.jar`:

1. Copie `config.properties.example` para `config.properties`.
2. Preencha `ssh.user`, `ssh.password` e `login.pins` (PINs separados por vírgula) com os valores reais.

Alternativamente, defina as variáveis de ambiente `DESTRAVAPDV_SSH_USER`, `DESTRAVAPDV_SSH_PASSWORD` e `DESTRAVAPDV_LOGIN_PINS`. O arquivo `config.properties` está no `.gitignore` e nunca deve ser commitado.

## Como abrir/compilar

O projeto foi criado no **NetBeans IDE** (build via Ant):

1. Abra a pasta do projeto no NetBeans (`File > Open Project`).
2. Compile/rode com `Clean and Build` / `Run` do próprio NetBeans, ou via linha de comando:
   ```bash
   ant jar
   ```
3. O `.jar` gerado fica em `dist/DestravaPDV.jar`, junto com a dependência `dist/lib/jsch-0.1.55.jar`.

## Observação de segurança

Este é um repositório **privado**, de uso interno da equipe de TI. As credenciais que antes estavam hardcoded no código-fonte (senha SSH dos PDVs e PINs de login) foram removidas e migradas para `config.properties`/variáveis de ambiente (veja acima). Como esses valores já haviam sido enviados ao GitHub, a senha SSH e os PINs antigos foram considerados comprometidos e devem ser rotacionados nos próprios PDVs e no aplicativo.
