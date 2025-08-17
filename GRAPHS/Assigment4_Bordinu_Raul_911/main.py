from Domain.OrientedGraph import OrientedGraph
from Service.ServiceOrintedGraph import ServiceOrientedGraph
from Service.ServiceOrintedGraph import create_random_graph
from UI.UI import UserInterface


def main():

    graph = OrientedGraph("graph_lab.txt")

    #create_random_graph(graph, 1000, 1200)

    graph_service = ServiceOrientedGraph(graph)

    ui = UserInterface(graph_service)

    ui.run_ui()


main()
