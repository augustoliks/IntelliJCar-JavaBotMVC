# IntelliJCar-JavaBotMVC :car: :oncoming_automobile:

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/2c1a809f61624d3fa2f99c3b77545986)](https://www.codacy.com/app/augustoliks/IntelliJCar-JavaBotMVC?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=augustoliks/IntelliJCar-JavaBotMVC&amp;utm_campaign=Badge_Grade)
[![chatroom icon](https://patrolavia.github.io/telegram-badge/chat.png)](https://telegram.me/jcar_bot)
[![License](https://img.shields.io/badge/License-BSD%202--Clause-orange.svg)](https://opensource.org/licenses/BSD-2-Clause)

#### Seu Carro na sua mão!

>> Bot telegram para permitir acesso as informações de seu automóvel ao seu smartphone via bot Telegram;

---

### Sobre:

>> Bot criado para fins acadêmicos, para o estudo e aplicação do padrão de arquitetura de software MVC. Proposto como trabalho semestral da disciplina Engenharia de Software III, lecionada pelo Profº Me. Giuliano Bertoti em 2018, na instituição de ensino Fatec Professor Jessen Vidal (São José dos Campos)

---

### Tecnologias utilizadas
>> Aplicativo de mensagens Telegram, Linguagem de Programação JAVA (padrão de arquitetura de software MVC) e Python (MicroFramework Flask), microocontrolador Arduino, módulo GSM e RaspberryPI.

---

### Referências:

>>> Documentação da API de acesso ao telegram:

- https://github.com/pengrad/java-telegram-bot-api

>>> Programações Auxiliares:

- https://github.com/giulianobertoti/EducationalTelegramBOT
- https://github.com/IHCF

---

# Funcionamento:

### **Logo do sistema:**

![description](ImgsFuncionamento/logo.jpg =250x250)

---

### **Tela de Descrição de Funcionamento:**

>> Primeira interação com o usuário;

![description](ImgsFuncionamento/00description.jpg =250x250)

---

### **Tela de Boas vindas:**

>> Tela que introduz o usuário ao aplicativo;

![hello](ImgsFuncionamento/01hello.jpg =250x250)

---

### **Função GPS:**

Mostra a localização do veículo;

![gps](ImgsFuncionamento/02gps.jpg =250x250)

---

### * **GoogleMaps:**

>> Ao clicar no link da mensagem do *Função Gps*, o bot encaminha para usuário, a localização de seu veículo via *GoogleMaps*:

![maps](ImgsFuncionamento/03googleMapsLink.jpg =250x250)

---

### **Bateria:**

>> Indica ao usuário um valor de 0 a 100 em relação diretamente proporcional a quantidade disponível de **bateria** do veículo;

![bat](ImgsFuncionamento/04batery.jpg =250x250)

---

### **Gasolina:**

>> Indica ao usuário um valor de 0 a 100 em relação diretamente proporcional a quantidade disponível de **gasolina** do veículo;

![gas](ImgsFuncionamento/05gasoline.jpg =250x250)

---

### **Franquia restante disponível:**

>> Indica ao usuário um valor correspondido em MB. a quantidade de franquia usada, e a quantidade de franquia total do respectivo pacote de dados atinente ao sistema (independente da operadora de telefonia, seguirá o mesmo padrão). *QUANTIDADE-GASTA* **|** *QUNATIDADE-TOTAL*

![net](ImgsFuncionamento/06netFrachise.jpg =250x250)

---

### **Nível de Sinal do Sistema:**
>> Indica ao usuário um valor de 0 a 100 em relação diretamente proporcional ao nível de área de acesso do módulo GSM do sistema com sua respectiva operadora;

![gsm](ImgsFuncionamento/07jcarBattery.jpg =250x250)

---

### **Dados em um intervalo de Tempo:**

>> Ao indicar um intervalo de tempo entre dois horários separados por um traço "**-**" (HHMM-HHMM), retornará para o usuário um link com todos pontos minuto a minuto de tal intervalo, e ao clicar nos pontos, terá em impresso na tela (*pop-up*) contendo todos os dados do veículo naquele respectivo momento.

![statusMaps](ImgsFuncionamento/statusMaps.jpg =250x250)


---

# Diagrama de Classes do Sistema:

![DiagramClass](ImgsFuncionamento/diagramClassJcar.png =250x250)
