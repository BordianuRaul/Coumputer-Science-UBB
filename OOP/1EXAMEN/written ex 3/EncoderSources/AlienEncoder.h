#pragma once
#include "Encoder.h"
#include <string>
using namespace std;

class AlienEncoder :
    public Encoder
{
private:
    string header;

public:

    AlienEncoder(string);

    string encode(string);
};

