# archivo metido en la carpeta python/whilelanguage/encoding
# importar desde terminal con from python.whilelanguage.encoding.enumN import enumN

from .godeldecoding import godeldecoding

def enumN(n: int) -> int:
    res=[]
    for i in range(n):
        res.append(godeldecoding(i))
    return res