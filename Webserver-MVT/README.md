# Para o webserver funcionar em rede local:
> Necessário deixar explicito os paramentros de funcionamento do webserver (porta, ip, etc..)
>> app.run(host='0.0.0.0', port=5000, debug=True)

# Para o webserver funcionar no Heroku:
> Para que funcione no heroku, não inserimos parâmentros de funcionamento do webserver. Inicia-se o projeto com **gunicorn**, pois ele que vai gerenciar toda parte de infraestrutura de rede:
>> app.run()

## Explicitar no Procfile: (obs* o nome da "main" do projeto tem que ser *app.py*)
>> web: gunicorn app:app


# Link do Webserver
> Este serviço esta alocado no HEROKU, neste link:
>> https://jcar-eng.herokuapp.com/
>> **Ou pode ser acessado localmente da seguinte maneira:**
>>> http://localhost:5000/

## *Get* Informações:
> Para receber informações:
>> https://jcar-eng.herokuapp.com/get/

## *Set* infomarções:
> Para inserir informações:
>> https://jcar-eng.herokuapp.com/set/gas=666&bat=666&lat=666&lon=666
>> https://jcar-eng.herokuapp.com/set/sal=123&dad=123
