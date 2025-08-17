
def f(n):

    s = 0
    for i in range(1, n * n + 1):

        j = 1
        while j < n:
            s = s + j
            j = j * 2
    return s