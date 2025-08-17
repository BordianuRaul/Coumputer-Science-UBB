#include "VolunteersTableModel.h"

VolunteersTableModel::VolunteersTableModel(Service& service) : service(service) {}

int VolunteersTableModel::rowCount(const QModelIndex& parent) const
{
	return this->service.getNrVols();
}

int VolunteersTableModel::columnCount(const QModelIndex& parent) const
{
	int nrCols = 4;
	return nrCols;
}

QVariant VolunteersTableModel::data(const QModelIndex& index, int role) const
{
    

	int row = index.row();
	int column = index.column();

	Volunteer volunteer = this->service.getVolunteers()[row];

	if (role == Qt::DisplayRole)
	{
		switch (column)
		{
		case 0:
		{
			return QString::fromStdString(volunteer.name);

		}
		case 1:
		{
			return QString::fromStdString(volunteer.email);
		}
		case 2:
		{
			return QString::fromStdString(volunteer.strInterests());
		}
		case 3:
		{
			return QString::fromStdString(volunteer.departament);
		}
		default: break;
		}
	}

    return QVariant();
}

QVariant VolunteersTableModel::headerData(int section, Qt::Orientation orientation, int role) const
{

	if (role == Qt::DisplayRole)
	{
		if (orientation == Qt::Horizontal)
		{
			switch (section) {
			case 0:
				return QString::fromStdString("Name");
			case 1:
				return QString::fromStdString("Email");
			case 2:
				return QString::fromStdString("Interests");
			case 3:
				return QString::fromStdString("Departament");
			default:break;
			}
		}
	}
	return QVariant();
}



