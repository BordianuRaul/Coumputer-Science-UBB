#include "AlienEncoder.h"

AlienEncoder::AlienEncoder(string header) : header(header){}

string AlienEncoder::encode(string message)
{

	auto encodedMessage = this->header + message;

	return encodedMessage;

}