# archivo metido en la carpeta python/whilelanguage/encoding
# importar desde terminal con from python.whilelanguage.encoding.enumWhile import enumWhile

from .n2while import n2while


def enumWhile(n: int) -> str:
    res=[]
    for i in range(n):
        res.append(n2while(i))
    return res

