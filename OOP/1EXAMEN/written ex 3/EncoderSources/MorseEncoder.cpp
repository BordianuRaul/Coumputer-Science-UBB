#include "MorseEncoder.h"

string MorseEncoder::encode(string message)
{

	string encodedMessage;

	int size = message.size();

	while (size)
	{
		encodedMessage += ".";
		size--;
	}

	return encodedMessage;

}