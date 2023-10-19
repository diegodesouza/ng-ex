export class ITabViews {
  tabs: [ITabView];
}

export class ITabView {
  heading: String;
  tabId?: String;
  isActive?: boolean;
  content?: String;
}
