from jcar.pop_model.get_set import GetSet

class Controller():
    def __init__(self):
        self.getter_setter = GetSet()

    #Get Most Recent
    def get_history_today(self):
        return self.getter_setter.get_history_today()

    def get_data_now(self):
        return self.getter_setter.get_data_now()

    #Get By Date
    def get_history(self, date):
        return self.getter_setter.get_history(date) 

    def get_data(self, date, index):
        return self.getter_setter.get_data(date, index)

    def get_last_date(self):
        return self.getter_setter.get_last_date()
