class Annotation():
    def __iter__(self):
        return self.__dict__.items().__iter__()

    @staticmethod
    def name():
        raise NotImplemented('Annotations must implement `name` method')
