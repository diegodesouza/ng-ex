export class ITabTiles {
    id: String;
    tabs: Array<ITab>;
}

export class ITab {
    label: String;
    icon: String;
    tabId?: String;
    analyticsText?: string;
    content?: String;
}
