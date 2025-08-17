#include "Model.h"

Model::Model(Service& service) : service(service)
{
}

int Model::rowCount(const QModelIndex& parent) const
{
	return this->service.getIdeas().size();
}

int Model::columnCount(const QModelIndex& parent) const
{
	return 4;
}

QVariant Model::data(const QModelIndex& index, int role) const
{
	

	int row = index.row();
	int column = index.column();

	Idea idea = this->service.getIdeas()[row];

	if (role == Qt::DisplayRole)
	{
		switch (column)
		{
		case 0:
			return QString::fromStdString(idea.getTitle());
		case 1:
			return QString::fromStdString(idea.getStatus());
		case 2:
			return QString::fromStdString(idea.getCreator());
		case 3:
			return QString::number(idea.getDuration());
		default:
			break;
		}
	}

	return QVariant();

}

bool Model::insertRows(int row, int count, const QModelIndex& parent)
{
	this->beginInsertRows(QModelIndex(), row, row + count - 1);
	this->endInsertRows();

	return true;
}


