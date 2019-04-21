import functools


def capitalized(items: list) -> list:
    return list(filter(lambda x: 'A' <= x[0] <= 'Z', items))


def longest(strings: list, from_start=True) -> object:
    return functools.reduce(lambda x, y: (x if len(x) > len(y) else y), strings[::-1]) if from_start else (
        functools.reduce(lambda i, j: i if len(i) > len(j) else j, strings))


def reverse(revlist: list) -> list:
    if not revlist:
        return []
    else:
        try:
            return [reverse(revlist[-1])] + reverse(revlist[:-1])
        except:
            return [revlist[-1]] + reverse(revlist[:-1])


def compress(compress_list: list) -> list:
    if not compress_list:
        return []
    elif len(compress_list) < 2:
        return compress_list
    else:
        if compress_list[0] not in compress_list[1:]:
            return [compress_list[0]] + compress(compress_list[1:])
        else:
            return compress(compress_list[1:])


def flatten(flatten_list: list) -> list:
    if not flatten_list:
        return []
    else:
        if isinstance(flatten_list[0], list):
            return flatten(flatten_list[0]) + flatten(flatten_list[1:])
        else:
            return [flatten_list[0]] + flatten(flatten_list[1:])


def composition(f: 'function', g: 'function') -> 'function':
    return lambda x: g(f(x))


def n_composition(*functions):
    return functools.reduce(lambda f, g: lambda x: f(g(x)), functions, lambda x: x)
