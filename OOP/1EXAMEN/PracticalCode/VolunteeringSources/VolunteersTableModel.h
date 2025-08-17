#pragma once

#include <QAbstractTableModel>
#include "Observer.h"
#include "Service.h"

class VolunteersTableModel : public Observer, public QAbstractTableModel
{

private:
	
	Service& service;
public:

	void update() override
	{

		beginResetModel();
		endResetModel();
	}

	VolunteersTableModel(Service& service);

	int rowCount(const QModelIndex& parent = QModelIndex()) const;

	int columnCount(const QModelIndex& parent = QModelIndex()) const;

	QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const;

	QVariant headerData(int section, Qt::Orientation orientation, int role = Qt::DisplayRole) const;

};

