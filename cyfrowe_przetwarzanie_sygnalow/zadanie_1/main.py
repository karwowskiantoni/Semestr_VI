import cli

if __name__ == '__main__':
    try:
        cli.main()
    except Exception:
        print("unknown error")
        cli.main()
